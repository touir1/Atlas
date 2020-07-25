using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Mvc;


namespace Atlas_frontend.Controllers
{
    public class EvaluationController : Controller
    {

        private IEvaluationService _evaluationService;
        private ISujetService _sujetService;
        private IUserService _userService;
        private ICompteService _compteService;

        public EvaluationController (IEvaluationService evaluationService , ISujetService sujetService , ICompteService compteService, IUserService userService)

        {
            _evaluationService = evaluationService;
            _sujetService = sujetService;
            _userService = userService;
            _compteService = compteService;


        }
        public async Task<IActionResult> Index()
        {
            //GET LIST OF sujets

            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<UserModel> users = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            List<SujetModel> sujets = await _sujetService.GetListAsync(HttpContext.Session);
            ViewBag.lstusers = users ?? new List<UserModel>();
            ViewBag.lstsujets = sujets ?? new List<SujetModel>(); ;

            return View();
        }

    }
    
}
