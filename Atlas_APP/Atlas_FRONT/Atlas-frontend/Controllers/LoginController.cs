using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Atlas_frontend.Controllers
{
    public class LoginController : Controller
    {
        private IRestAPIClient _restApiClient;
        public LoginController(IRestAPIClient restAPIClient)
        {
            _restApiClient = restAPIClient;
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
            Boolean x = await _restApiClient.PostAsync<FormationModel>("formation", new FormationModel
            {
                Libelle = "aaaaaaaaaaa"
            }) ;

            List<FormationModel> s = await _restApiClient.GetAsync<List<FormationModel>>("formation");


            FormationModel f = await _restApiClient.PutAsync<FormationModel>("formation", new FormationModel
            { Id = 1 ,
                Libelle = "bbbb"
            }) ;


            FormationModel w = await _restApiClient.GetAsync<FormationModel>($"formation/{s[0].Id}");

            Boolean z = await _restApiClient.DeleteAsync<FormationModel>($"formation/{s[0].Id}");


            


           

            return RedirectToAction("Index","Home");
          
        }
    }
}
