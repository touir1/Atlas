using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Service;
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
        private IChoixService _choixservice;




        public ReponseController(IEvaluationService evaluationService, ISujetService sujetService, IChoixService choixservice, ICompteService compteService, IUserService userService, IReponseService reponseService, IQuestionService questionService)

        {
            _evaluationService = evaluationService;
            _sujetService = sujetService;
            _userService = userService;
            _compteService = compteService;
            _reponseService = reponseService;
            _questionService = questionService;
            _choixservice = choixservice;


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



        // GET: /<controller>/
        public async Task<IActionResult> view(long id)
        {
            //GET LIST OF USER By manager
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            
           

            List<UserModel> result = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));

            List<EvaluationModel> eval = await _evaluationService.GetListEvalByUser(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            EvaluationModel evaluation = await _evaluationService.GetAsync(HttpContext.Session, id);
        

            List<ResponseModel> rep = await _reponseService.GetReponseByEvalAsync(HttpContext.Session, id);
            List<ReponsePass>  Lstrepo = new List<ReponsePass>();

            foreach(ResponseModel item in rep)
            {
                ReponsePass rp = new ReponsePass();
                rp.sujets = item.Question.Sujet;
                rp.questions = item.Question;
                rp.evals = item.Evaluation;
                if (item.Question.Type != "text"){
                    List<ChoixModel> CM = await _choixservice.getChoixByQuestion(HttpContext.Session, id);

                    rp.choix = CM;

                }


                Lstrepo.Add(rp);

            }

            ViewBag.lstEval = Lstrepo ?? new List<ReponsePass>();


            return View();
        }
    }
}
