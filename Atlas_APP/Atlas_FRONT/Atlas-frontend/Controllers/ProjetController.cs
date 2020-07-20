using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Mvc;
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
        public IActionResult List()
        {
            //GET LIST OF USER By manager 
            return View();
        }
        //POST: /<controller>
        [HttpPost]
        public  IActionResult AddProject([Bind("Titre", "DateCreation","DateCloture")] ProjetModel projet, List<long> membres)
        {
            try
            {
                 _projetService.AddAsync(HttpContext.Session, projet);
                return View();
            } catch(Exception e)
            {
                //return null;
                return View();
            }
              

            
        }
    }
}
