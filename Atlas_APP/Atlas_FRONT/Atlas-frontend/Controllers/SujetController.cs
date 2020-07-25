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
        private IQuestionService _questionService;
        private IChoixService _choixService;

        public SujetController(ISujetService sujetService, IQuestionService questionService, IChoixService choixService )

        {
            _sujetService = sujetService;
            _questionService = questionService;
            _choixService = choixService;
        }


        // GET: /<controller>/
        public IActionResult Index()
        {
            //GET LIST OF USER By manager 
            return View();
        }
        // GET: /<controller>/
        public async Task<IActionResult> ListAsync()
        {
            //GET LIST OF USER By manager

            List<SujetModel> sujets = await _sujetService.GetListAsync(HttpContext.Session);
            ViewBag.lstsujets = sujets ?? new List<SujetModel>(); ;

            return View();
        }

        //POST: /<controller>
        [HttpPost]
        public async Task<IActionResult> AddSujet([Bind("Titre", "Coeficient", "Noter")] SujetModel sujet)
        {
            try
            {


                SujetModel suj = await _sujetService.AddAsync(HttpContext.Session, sujet);
                return RedirectToAction("List", "Sujet");
            }
            catch (Exception e)
            {
                //return null;
                return View();
            }



        }
        // GET: User/Edit/5
        public async Task<IActionResult> EditAsync(long id)
        {
            SujetModel sujets = await _sujetService.GetAsync(HttpContext.Session, id);

            ViewData.Model = sujets;
            return View();
        }
        //POST: /<controller>
        [HttpPost]
        public async Task<IActionResult> Addquestion(List<PassModel> questions, SujetModel sujet , QuestionModel question)
        {
            

            try
            {
                foreach (PassModel item in questions)
                {
                    QuestionModel qst = new QuestionModel();
                    qst.Libelle = item.Libelle;
                    qst.Sujet = sujet;
                    qst.Type = item.Type;
                    qst = await _questionService.AddAsync(HttpContext.Session, qst);
                    foreach(String element in item.Choix)
                    {
                        ChoixModel choix = new ChoixModel();
                        choix.Libelle = element;
                        choix.Question = qst;
                        choix = await _choixService.AddAsync(HttpContext.Session, choix);
                    }
                }
                return Json(new { someValue = "ok" });


            }
            catch (Exception e)
            {
                //return null;
                return Json(new { someValue = "ko" });
            }


        }

        // POST: Projet/Edit/5
        [HttpPost]
        public async Task<IActionResult> EditAsync(long id, SujetModel sujets)
        {
            try
            {
                sujets.Id = id;

                if (sujets.Noter == false)
                {
                    sujets.Coeficient = 0;
                }

                await _sujetService.UpdateAsync(HttpContext.Session, sujets);

                return RedirectToAction("List", "Sujet");
            }
            catch (Exception e)
            {
                //return null;
                return View();
            }

        }

        // POST: Sujet/Delete/5
        public async Task<ActionResult> DeleteAsync(long id)
        {
            try
            {
                await _sujetService.DeleteAsync(HttpContext.Session, id);
                return RedirectToAction("List", "Sujet");
            }
            catch
            {
                return View();
            }
        }

    }



}