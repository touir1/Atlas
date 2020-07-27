using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.Mail;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Rotativa;

namespace Atlas_frontend.Controllers
{
    public class FraisController : Controller
    {
        private IMissionService _missionService;
        private IProjetService _projetService;
        private ICompteService _compteService;
        private IUserService _userService;
        private IFraisService _fraisService;
        private IWebHostEnvironment _webHostEnvironment;
        private IMailService _mailService;

        public FraisController(IMissionService missionService, IProjetService projetService, ICompteService compteService, IUserService userService,IFraisService fraisService, IWebHostEnvironment hostEnvironment, IMailService mailService)
        {
            _missionService = missionService;
            _projetService = projetService;
            _compteService = compteService;
            _userService = userService;
            _fraisService = fraisService;
            _webHostEnvironment = hostEnvironment;
            _mailService = mailService;
        }

        // GET: Frais
        public async Task<ActionResult> IndexAsync()
        {
            List<FraisModel> frais = await _fraisService.GetListAsync(HttpContext.Session);
            ViewData.Model = frais;
            return View();
        }

        // GET: Frais/Details/5
        public async Task<ActionResult> DetailsAsync(int id)
        {
            FraisModel frais = await  _fraisService.GetAsync(HttpContext.Session, id);
            ViewData.Model = frais;
            return View();
        }

        // GET: Frais/Create
        public async Task<ActionResult> CreateAsync()
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<MissionModel> result = await _missionService.GetListAsync(HttpContext.Session);
            ViewBag.listmission = result ?? new List<MissionModel>();
            List<UserModel> listusers = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            listusers = listusers ?? new List<UserModel>();
            listusers.Add(compte.User);
            ViewBag.userlist = listusers;
            return View();
        }

        // POST: Frais/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> CreateAsync(FraisModel model, long? missionId,long? userId)
        {
            try
            {
                if (!ModelState.IsValid)
                {
                    CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                    List<MissionModel> result = await _missionService.GetListAsync(HttpContext.Session);
                    ViewBag.listmission = result ?? new List<MissionModel>();
                    List<UserModel> listusers = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
                    listusers = listusers ?? new List<UserModel>();
                    listusers.Add(compte.User);
                    ViewBag.userlist = listusers;
                    return View();
                }
                string uniqueFileName = UploadedFile(model);
                model.Justificatif = uniqueFileName;
                model.Mission = new MissionModel { Id = missionId };
                UserModel userSelected =await _userService.GetAsync(HttpContext.Session, userId);
                model.User = userSelected;

                await  _fraisService.AddAsync(HttpContext.Session,model);
                return RedirectToAction("Index");
            }
            catch
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                List<MissionModel> result = await _missionService.GetListAsync(HttpContext.Session);
                ViewBag.listmission = result ?? new List<MissionModel>();
                List<UserModel> listusers = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
                listusers = listusers ?? new List<UserModel>();
                listusers.Add(compte.User);
                ViewBag.userlist = listusers;

                return View();
            }
        }

        // GET: Frais/Edit/5
        public async Task<ActionResult> EditAsync(int id)
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<MissionModel> result = await _missionService.GetListAsync(HttpContext.Session);
            ViewBag.listmission = result ?? new List<MissionModel>();
            List<UserModel> listusers = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            listusers.Add(compte.User);
            ViewBag.userlist = listusers ?? new List<UserModel>();

            FraisModel fees = await _fraisService.GetAsync(HttpContext.Session, id);
            ViewData.Model = fees;
            return View();
        }

        // POST: Frais/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> EditAsync(int id, FraisModel model, long? missionId)
        {
            try
            {
                FraisModel fees = await _fraisService.GetAsync(HttpContext.Session, id);

                if (!ModelState.IsValid)
                {
                    return View();
                }
                MissionModel mission = await _missionService.GetAsync(HttpContext.Session, missionId);
                
                model.Id = id;
                model.Mission= mission;
                model.User = fees.User;
                string uniqueFileName = UploadedFile(model);
                model.Justificatif = uniqueFileName;

                await _fraisService.UpdateAsync(HttpContext.Session,model);

                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: Frais/Delete/5
        public async Task<ActionResult> DeleteAsync(int id)
        {
            FraisModel fees = await _fraisService.GetAsync(HttpContext.Session, id);
            ViewData.Model = fees;
            return View();
        }

        // POST: Frais/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> DeleteAsync(int id, IFormCollection collection)
        {
            try
            {
               await _fraisService.DeleteAsync(HttpContext.Session, id);

                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        public async Task<ActionResult> Index2Async()
        {
            List<FraisModel> frais = await _fraisService.GetListAsync(HttpContext.Session);
            ViewData.Model = frais;
            return View();
        }

        public async Task<ActionResult> ValiderAsync(int id)
        {
            FraisModel frais = await _fraisService.GetAsync(HttpContext.Session,id);
            frais.Status = "Remboursé";
            await _fraisService.UpdateAsync(HttpContext.Session, frais);
            await _mailService.SendMailAsync(HttpContext.Session, frais.User.Email, "[Atlast] Remboursement","",false);
            
            return RedirectToAction("Index2");
        }

      //  public ActionResult GeneratePDF()
       // {
          //  return new ActionasPDF("Details");
        //}

        private string UploadedFile(FraisModel model)
        {
            string uniqueFileName = null;

            if (model.Justifiatiffile != null)
            {
                string uploadsFolder = Path.Combine(_webHostEnvironment.WebRootPath, "images/justificatifs");
                uniqueFileName = Guid.NewGuid().ToString() + "_" + model.Justifiatiffile.FileName;
                string filePath = Path.Combine(uploadsFolder, uniqueFileName);
                using (var fileStream = new FileStream(filePath, FileMode.Create))
                {
                    model.Justifiatiffile.CopyTo(fileStream);
                }
            }
            return uniqueFileName;
        }
    }
}