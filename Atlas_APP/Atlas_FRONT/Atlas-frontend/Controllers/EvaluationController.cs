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
    public class EvaluationController : Controller
    {

        private IEvaluationService _evaluationService;
        private ISujetService _sujetService;
        private IUserService _userService;
        private ICompteService _compteService;
        private IReponseService _reponseService;
        private IQuestionService _questionService;
        

        public EvaluationController (IEvaluationService evaluationService , ISujetService sujetService , ICompteService compteService, IUserService userService, IReponseService reponseService , IQuestionService questionService )

        {
            _evaluationService = evaluationService;
            _sujetService = sujetService;
            _userService = userService;
            _compteService = compteService;
            _reponseService = reponseService;
            _questionService = questionService;


        }


        // GET: /<controller>/
        public async Task<IActionResult> ListeAsync()
        {
            //GET LIST OF USER By manager

            List<EvaluationModel> eval = await _evaluationService.GetListAsync(HttpContext.Session);
            ViewBag.lsteval = eval ?? new List<EvaluationModel>();
            

            return View();
        }


        public async Task<IActionResult> Index()
        {
            //GET LIST OF sujets

            
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<UserModel> result = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            List<SujetModel> sujets = await _sujetService.GetListAsync(HttpContext.Session);
            ViewBag.lstsujets = sujets ?? new List<SujetModel>();
            ViewBag.lstUser = result ?? new List<UserModel>();

            return View();
        }

       

        //POST: /<controller>
        [HttpPost]
        public async Task<IActionResult> AddEval([Bind("Titre")] EvaluationModel evaluation, List<long> membres, List<long> list )
        {
            try
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                evaluation.CreatedBy = compte.User;
                evaluation.Status = "Created";
                EvaluationModel evaluations = await _evaluationService.AddAsync(HttpContext.Session, evaluation);
               
                


                foreach (long item in list)
                {
                    List<QuestionModel> qst = await _questionService.GetQuestionBySujet(HttpContext.Session, item);
                  
                    foreach (QuestionModel element in qst)
                    {
                        ResponseModel rps = new ResponseModel();

                        rps.Evaluation = evaluations;
                        rps.Question = element;

                        await _reponseService.AddAsync(HttpContext.Session, rps);

                    }
                }

                    foreach (long item in membres)
                {

                      await _evaluationService.AffecterUserToEvaluationAsync(HttpContext.Session, evaluations.Id, item); 
                }


             

                return RedirectToAction("Liste", "Evaluation");
            }
            catch (Exception e)
            {
                //return null;
                return View();
            }


            
            }

        // GET: User/Edit/5
        public async Task<IActionResult> Edit(long id)
        {
            EvaluationModel evals = await _evaluationService.GetAsync(HttpContext.Session, id);
            CompteModel compte =  _compteService.GetConnectedCompte(HttpContext.Session);
            SujetModel sujets = await _sujetService.GetAsync(HttpContext.Session, id);

            List<UserModel> lstUser = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            var lstUserByEval = (await _evaluationService.GetListUserByEvalAsync(HttpContext.Session, evals.Id.GetValueOrDefault(0))).Select(s => new {
                Id = s.Id,
                Nom = s.Nom,
                Prenom = s.Prenom,
                Selected = true
            }).ToList();
            var listUserTolal =
                lstUser.Where(w => lstUserByEval == null || lstUserByEval.Where(x => x.Id == w.Id).Count() == 0)
                .Select(s => new {
                    Id = s.Id,
                    Nom = s.Nom,
                    Prenom = s.Prenom,
                    Selected = false
                }).Concat(lstUserByEval).ToList();
            //for(int i = 0; i < listUserTolal.Count();i++) { }

            ViewBag.lstUser = listUserTolal ?? new[] {
            new {Id = (long?)0,
                    Nom = "",
                    Prenom = "",
                    Selected = false }
            }.ToList();

            
            List<SujetModel> lstsujet = await _sujetService.GetListAsync(HttpContext.Session);
            var lstSujetsByEval = (await _evaluationService.GetListSujetByEvalAsync(HttpContext.Session, evals.Id.GetValueOrDefault(0))).Select(s => new {
                Id = s.Id,
                Titre = s.Titre,
                Selected = true
            }).ToList();
            var listTotal =
                lstsujet.Where(w => lstSujetsByEval == null || lstSujetsByEval.Where(x => x.Id == w.Id).Count() == 0)
                .Select(s => new {
                    Id = s.Id,
                    Titre = s.Titre,
                    Selected = false
                }).Concat(lstSujetsByEval).ToList();
            //for(int i = 0; i < listUserTolal.Count();i++) { }

            ViewBag.lstsujet = listTotal ?? new[] {
            new {Id = (long?)0,
                    Titre = "",
                     Selected = false }
            }.ToList();
            
            ViewData.Model = evals;
            return View();
        }

        private object Select(Func<object, object> p)
        {
            throw new NotImplementedException();
        }


        // POST: Sujet/Delete/5
        public async Task<ActionResult> DeleteAsync(long id)
        {
            try
            {
                await _evaluationService.DeleteAsync(HttpContext.Session, id);
                return RedirectToAction("Liste", "Evaluation");
            }
            catch
            {
                return View();
            }


        }


        //POST: /<controller>
        [HttpPost]
        public async Task<IActionResult> Edit(EvaluationModel evaluation, List<long> membres, List<long> sujets)
        {
            try
            {
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                 await _evaluationService.UpdateAsync(HttpContext.Session, evaluation);




                foreach (long item in sujets)
                {
                    List<QuestionModel> qst = await _questionService.GetQuestionBySujet(HttpContext.Session, item);

                    foreach (QuestionModel element in qst)
                    {
                        ResponseModel rps = new ResponseModel();

                        rps.Evaluation = evaluation;
                        rps.Question = element;

                        await _reponseService.AddAsync(HttpContext.Session, rps);

                    }
                }

                foreach (long item in membres)
                {

                    await _evaluationService.AffecterUserToEvaluationAsync(HttpContext.Session, evaluation.Id, item);
                }




                return RedirectToAction("Liste", "Evaluation");
            }
            catch (Exception e)
            {
                //return null;
                return View();
            }



        }


    }
    

}
