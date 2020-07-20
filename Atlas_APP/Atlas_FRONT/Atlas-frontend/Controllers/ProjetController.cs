using Atlas_frontend.Models;
using Atlas_frontend.Services;
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
        public ProjetController(IProjetService projetService)
        {
            _projetService = projetService;
          
        }
        // GET: /<controller>/
        public IActionResult Index()
        {
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
        public  IActionResult AddProject([Bind("Titre", "DateCreation","DateCloture","Membres")] ProjetModel projet)
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
