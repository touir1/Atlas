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
            return View();
        }
    }
}
