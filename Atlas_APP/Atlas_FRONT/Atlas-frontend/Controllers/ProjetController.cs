using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Razor.Language;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Controllers
{
    public class ProjetController : Controller
    {
        private IProjetService _projetService;
        private IUserService _userService;
        private ICompteService _compteService;
        public ProjetController(IProjetService projetService, IUserService userService, ICompteService compteService)
        {
            _projetService = projetService;
            _userService = userService;
            _compteService = compteService;
          
        }
        // GET: /<controller>/
        public async Task<IActionResult> Index()
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<UserModel> result = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.lstUser = result ?? new List<UserModel>();
            
          //GET LIST OF USER By manager 
            return View();
        }
        // GET: User/Edit/5
        public async Task<IActionResult> Edit(long id)
        {
            ProjetModel projet = await _projetService.GetAsync(HttpContext.Session, id);
            var lstUserByProjet = (await _projetService.GetListUserByProjectAsync(HttpContext.Session, projet.Id.GetValueOrDefault(0))).Select(s => new {
                Id = s.Id,
                Nom = s.Nom,
                Prenom = s.Prenom,
                Selected = true
            }).ToList();
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<UserModel> lstUser = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            var listUserTolal =
                lstUser.Where(w => lstUserByProjet == null || lstUserByProjet.Where(x => x.Id == w.Id).Count() == 0)
                .Select(s => new { 
                    Id = s.Id,
                    Nom = s.Nom,
                    Prenom = s.Prenom,
                    Selected = false
                }).Concat(lstUserByProjet).ToList();
            //for(int i = 0; i < listUserTolal.Count();i++) { }

            ViewBag.lstUser = listUserTolal ?? new[] {
            new {Id = (long?)0,
                    Nom = "",
                    Prenom = "",
                    Selected = false }
            }.ToList();
            ViewData.Model = projet;
            return View();
        }
        public async Task<IActionResult> List()
        {
            //GET LIST OF USER By manager 
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<ProjetModel> result = await _projetService.GetListProjectByManager(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.lstmanager = result ?? new List<ProjetModel>();
            return View();
        }
        //POST: /<controller>
        [HttpPost]
        public  async Task<IActionResult> AddProject([Bind("Titre", "DateCreation","DateCloture")] ProjetModel projet, List<long> membres)
        {
            try
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                projet.createdBy = compte.User;
                ProjetModel prj = await _projetService.AddAsync(HttpContext.Session, projet);
                foreach(long item in membres)
                {
                    await _projetService.AffecterUserToProjetAsync(HttpContext.Session, prj.Id, item);
                }
                return RedirectToAction("List", "Projet");
            } catch(Exception e)
            {
                //return null;
                return View();
            }
              

            
        }
        // POST: Projet/Edit/5
        [HttpPost]
        public async Task<IActionResult> EditAsync(long id,ProjetModel projet, List<long> membres)
        {
            try
            {   
                projet.Id = id;
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                projet.createdBy = compte.User;
                await _projetService.UpdateAsync(HttpContext.Session, projet);
                await _projetService.RemoveAllUserFromProjetAsync(HttpContext.Session, projet.Id);
                foreach (long item in membres)
                {
                    await _projetService.AffecterUserToProjetAsync(HttpContext.Session, projet.Id, item);
                }

                return RedirectToAction("List", "Projet");
            }
            catch (Exception e)
            {
                //return null;
                return View();
            }



        }
        //POST: /<controller>
        [HttpPost]
        public Boolean AddRbrique(List<RubriqueModel> rubriques)
        {
            try
            {
                return true;
            }
            catch (Exception e)
            {
                //return null;
                return false;
            }



        }
        //POST:cloturer Projet
        public async Task<ActionResult> Cloturer(long id)
        {
            try
            {
                await _projetService.CloturerProjet(HttpContext.Session, id);
                return RedirectToAction("List", "Projet");
            }
            catch(Exception e)
            {
                return View();
            }
            
        }
    }

}
