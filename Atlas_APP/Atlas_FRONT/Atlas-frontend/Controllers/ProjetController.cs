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
        // GET: /<controller>/
        public async Task <IActionResult> List()
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
    }

}
