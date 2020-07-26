using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Razor.Language;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Atlas_frontend.Controllers
{
    public class RapportController : Controller
    {
        private IProjetService _projetService;
        private ICompteService _compteService;
        private IRubriqueService _rubriqueService;
        private IRapportService _rapportService;

        CultureInfo cultureInfo = CultureInfo.CurrentCulture;
        public RapportController(IProjetService projetService, ICompteService compteService, IRubriqueService rubriqueService, IRapportService rapportService) { 

            _projetService = projetService;
            _compteService= compteService;
            _rubriqueService = rubriqueService;
            _rapportService = rapportService;
           


            
        }
        // GET: /<controller>/
        public async Task<IActionResult> Index()
        {

            var WeekOfYear = cultureInfo.Calendar.GetWeekOfYear(DateTime.Now, CalendarWeekRule.FirstDay, DayOfWeek.Monday);
            string dayOfWeek = DateTime.Now.ToString("dddd", new System.Globalization.CultureInfo("fr-FR"));
            var monthOfYear = cultureInfo.Calendar.GetMonth(DateTime.Now);
            var year = cultureInfo.Calendar.GetYear(DateTime.Now);
            DayOfWeek fdow = CultureInfo.CurrentCulture.DateTimeFormat.FirstDayOfWeek;
            DateTime start = DateTime.Today.AddDays(-(DateTime.Today.DayOfWeek - fdow));
            ViewBag.WeekOfYear = WeekOfYear;
            ViewBag.DayOfweek = dayOfWeek;
            ViewBag.monthOfYear = monthOfYear;
            ViewBag.year = year;

            ViewBag.start = start.AddDays(1);

            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List <ProjetModel> projets = await _projetService.GetListProjectByUser(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.projets = projets ?? new List<ProjetModel>();

            return View();
        }
        //GET: /<controller>

        [HttpPost]
        public async Task<ActionResult> GetRubrique(ProjetModel projet)
        {
            try
            {
                List<RubriqueModel> LstRubrique = await _rubriqueService.GetListRubriqueByUser(HttpContext.Session, projet.Id);

                return Json(LstRubrique);
            }
            catch (Exception e)
            {
                //return null;
                return Json(new { someValue = "ko" });


            }



        }
        [HttpPost]
        public async Task<ActionResult> PostRapport(List<CustomModel> customModel) { 
           try
            {
                DayOfWeek fdow = CultureInfo.CurrentCulture.DateTimeFormat.FirstDayOfWeek;
                DateTime start = DateTime.Today.AddDays(-(DateTime.Today.DayOfWeek - fdow));
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                foreach (CustomModel item in customModel)
                { 
                    for(int i =1; i<=5; i++)
                    {
                        RapportModel rapport = new RapportModel();
                        rapport.Semaine = item.Semaine;
                        rapport.Rubrique = item.Rubrique;
                        rapport.Mois = item.Mois;
                        rapport.Annee = item.Annee;
                        rapport.DateCreation = DateTime.Now;
                        rapport.User = compte.User;
                        rapport.JoursSemaine = i;
                        switch (i)
                        {
                            case 1:
                                rapport.Duree = item.LundiDuree;
                                rapport.DateImputation = start.AddDays(1);
                                break;
                            case 2:
                                rapport.Duree = item.MardiDuree;
                                rapport.DateImputation = start.AddDays(2);
                                break;
                            case 3:
                                rapport.Duree = item.MercrediDuree;
                                rapport.DateImputation = start.AddDays(3);
                                break;
                            case 4:
                                rapport.Duree = item.JeudiDuree;
                                rapport.DateImputation = start.AddDays(4);
                                break;
                            case 5:
                                rapport.Duree = item.VendrediDuree;
                                rapport.DateImputation = start.AddDays(5);
                                break;
                            default:
                                break;
                        }

                        await _rapportService.AddAsync(HttpContext.Session, rapport);


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
    }

}
