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

        public EvaluationController (IEvaluationService evaluationService , ISujetService sujetService)

        {
            _evaluationService = evaluationService;
            _sujetService = sujetService;
        }
        public async Task<IActionResult> Index()
        {
            //GET LIST OF sujets

            List<SujetModel> sujets = await _sujetService.GetListAsync(HttpContext.Session);
            ViewBag.lstsujets = sujets ?? new List<SujetModel>(); ;

            return View();
        }

    }
    
}
