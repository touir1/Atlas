using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.Attributes;
using Atlas_frontend.Utils.Mail;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Razor.Extensions;

namespace Atlas_frontend.Controllers
{
    public class AbsenceController : Controller
    {
        private IAbsenceService _absenceService;
        private ICompteService _compteService;
        private IMailService _mailService;

        public AbsenceController(IAbsenceService absenceService, ICompteService compteService, IMailService mailService)
        {
            _absenceService = absenceService;
            _compteService = compteService;
            _mailService = mailService;
        }
        // GET: Absence
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> IndexAsync()
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<AbsenceModel> myAbsences = await _absenceService.GetListAbsenceForUser(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.myAbsences = myAbsences;
            List<AbsenceModel> toValidateAbsencesManager = await _absenceService.GetListAbsenceByStatusForManager(HttpContext.Session, AbsenceStatus.AValider ,compte.User.Id.GetValueOrDefault(0));
            ViewBag.toValidateAbsencesManager = toValidateAbsencesManager;
            List<AbsenceModel> toValidateAbsencesHR = await _absenceService.GetListAbsenceByStatus(HttpContext.Session, AbsenceStatus.AValider);
            toValidateAbsencesHR = toValidateAbsencesHR ?? new List<AbsenceModel>();
            toValidateAbsencesHR = toValidateAbsencesHR.Where(w => w.Type != AbsenceType.Conge).ToList();
            ViewBag.toValidateAbsencesHR = toValidateAbsencesHR;
            float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.soldeCongee = soldeCongee;

            return View();
        }

        // GET: Absence/Create
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> CreateAsync()
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.soldeCongee = soldeCongee;

            return View();
        }

        // POST: Absence/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> CreateAsync(AbsenceModel absence)
        {
            try
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);


                if (!ModelState.IsValid)
                {
                    float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
                    ViewBag.soldeCongee = soldeCongee;

                    return View();
                }
                absence.Status = AbsenceStatus.AValider;
                absence.User = compte.User;
                await _absenceService.AddAsync(HttpContext.Session, absence);

                if(absence.Type == AbsenceType.Conge && compte.User.Chef != null)
                {
                    await _mailService.SendMailAsync(HttpContext.Session, compte.User.Chef.Email, "[Atlas] Demande de congé", "DemandeConge.html", new Dictionary<string, string> {
                        {"#_USER_FULLNAME_#",compte.User.FullName},
                        {"#_MANAGER_FULLNAME_#",compte.User.Chef.FullName}
                    });
                }

                return RedirectToAction("Index");
            }
            catch
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
                ViewBag.soldeCongee = soldeCongee;

                return View();
            }
        }

        // GET: Absence/Edit/5
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> EditAsync(int id)
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.soldeCongee = soldeCongee;

            AbsenceModel absence = await _absenceService.GetAsync(HttpContext.Session, id);
            ViewData.Model = absence;

            return View();
        }

        // POST: Absence/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> EditAsync(int id, AbsenceModel absence)
        {
            try
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);

                if (!ModelState.IsValid)
                {
                    float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
                    ViewBag.soldeCongee = soldeCongee;

                    return View();
                }
                absence.Status = AbsenceStatus.AValider;
                absence.User = compte.User;
                await _absenceService.UpdateAsync(HttpContext.Session, absence);

                return RedirectToAction("Index");
            }
            catch
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                float soldeCongee = await _absenceService.GetSoldeCongee(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
                ViewBag.soldeCongee = soldeCongee;

                return View();
            }
        }

        // GET: Absence/Delete/5
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> DeleteAsync(int id)
        {
            AbsenceModel absence = await _absenceService.GetAsync(HttpContext.Session, id);
            ViewData.Model = absence;

            return View();
        }

        [Authorize(new[] { RankEnum.HR, RankEnum.Manager })]
        public async Task<ActionResult> ValidateAsync(int id)
        {
            AbsenceModel absence = await _absenceService.GetAsync(HttpContext.Session, id);
            absence.Status = AbsenceStatus.Valide;
            await _absenceService.UpdateAsync(HttpContext.Session, absence);

            await _mailService.SendMailAsync(HttpContext.Session, absence.User.Email, "[Atlas] Demande de congé validé", "ValidationDemandeConge.html", new Dictionary<string, string> {
                {"#_USER_FULLNAME_#",absence.User.FullName}
            });

            return RedirectToAction("Index");
        }

        [Authorize(new[] { RankEnum.HR, RankEnum.Manager })]
        public async Task<ActionResult> RefuseAsync(int id)
        {
            AbsenceModel absence = await _absenceService.GetAsync(HttpContext.Session, id);
            absence.Status = AbsenceStatus.Refuse;
            await _absenceService.UpdateAsync(HttpContext.Session, absence);

            await _mailService.SendMailAsync(HttpContext.Session, absence.User.Email, "[Atlas] Demande de congé refusé", "RefusDemandeConge.html", new Dictionary<string, string> {
                {"#_USER_FULLNAME_#",absence.User.FullName}
            });

            return RedirectToAction("Index");
        }

        // POST: Absence/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Connected })]
        public async Task<ActionResult> DeleteAsync(int id, AbsenceModel absence)
        {
            try
            {
                await _absenceService.DeleteAsync(HttpContext.Session, id);

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}