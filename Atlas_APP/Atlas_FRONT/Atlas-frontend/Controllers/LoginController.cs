using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Atlas_frontend.Controllers
{
    public class LoginController : Controller
    {
        private ICompteService _compteService;
        public LoginController(ICompteService compteService)
        {
            _compteService = compteService;
        }
        // GET: /<controller>/
        public IActionResult Index()
        {
            return View();
        }
        //POST: /<controller>
        [HttpPost]
        public async Task<IActionResult> IndexAsync([Bind("Username","Password")] CompteModel compte)
        {
           try {

            CompteModel loggedIn = await _compteService.LoginAsync(HttpContext.Session, compte.Username, compte.Password);  

            //RestApiResponse<List<FormationModel>> s = await _restApiClient.GetAsync<List<FormationModel>>(HttpContext.Session, "formation");

            return RedirectToAction("Index", "Home");
           }
           catch(Exception e) {
               return View();
           }
           
          
        }
    }
}
