using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Mvc;

namespace Atlas_frontend.Controllers


{
    public class SujetController : Controller
    {
        private ISujetService _sujetService;

        public SujetController(ISujetService sujetService)

        {
            _sujetService = sujetService;
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
        public IActionResult AddSujet([Bind("Titre", "Coefficient", "Note")] SujetModel sujet)
        {
            try
            {
                _sujetService.AddAsync(HttpContext.Session, sujet);
                return View();
            }
            catch (Exception e)
            {
                //return null;
                return View();
            }

        }
    }
}
