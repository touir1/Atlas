using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
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
        public async Task<IActionResult> SignInAsync([Bind("Username","Password")] CompteModel compte)
        {
            // after login we get the token
            string token = "fake_token";
            HttpContext.Session.SetString(RestAPIClient.TokenKey, token);

            RestApiResponse<Boolean> r = await _restApiClient.PostAsync<FormationModel,Boolean>(HttpContext.Session,"formation", new FormationModel
            {
                Libelle = "aaaaaaaaaaa"
            });

            RestApiResponse<List<FormationModel>> s = await _restApiClient.GetAsync<List<FormationModel>>(HttpContext.Session, "formation");


            RestApiResponse<Boolean> f = await _restApiClient.PutAsync<FormationModel, Boolean>(HttpContext.Session, "formation", new FormationModel
            { 
                Id = 1 ,
                Libelle = "bbbb"
            });


            RestApiResponse<FormationModel> w = await _restApiClient.GetAsync<FormationModel>(HttpContext.Session, $"formation/{s.Result[0].Id}");

            RestApiResponse<Boolean> z = await _restApiClient.DeleteAsync<FormationModel, Boolean>(HttpContext.Session, $"formation/{s.Result[0].Id}");
           

            return RedirectToAction("Index","Home");
          
        }
    }
}
