using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Atlas_frontend.Services.Implementation;
using Microsoft.AspNetCore.Mvc;

namespace Atlas_frontend.Controllers
{
    public class ReponseController : Controller
    {

        private IEvaluationService _evaluationService;
        private ISujetService _sujetService;
        private IUserService _userService;
        private ICompteService _compteService;
        private IReponseService _reponseService;
        private IQuestionService _questionService;


        public ReponseController(IEvaluationService evaluationService, ISujetService sujetService, ICompteService compteService, IUserService userService, IReponseService reponseService, IQuestionService questionService)

        {
            _evaluationService = evaluationService;
            _sujetService = sujetService;
            _userService = userService;
            _compteService = compteService;
            _reponseService = reponseService;
            _questionService = questionService;


        }
        public IActionResult IndexAsync()
        {

            
           
    
            return View();
        }

        // GET: /<controller>/
        public async Task<IActionResult> ListeAsync()
        {
            //GET LIST OF USER By manager
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);

            List<UserModel> result = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));

            List<EvaluationModel> eval = await _evaluationService.GetListEvalByUser(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.lstEval = eval ?? new List<EvaluationModel>();


            return View();
        }
    }
}
