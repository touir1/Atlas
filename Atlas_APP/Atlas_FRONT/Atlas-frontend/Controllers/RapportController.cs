using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Atlas_frontend.Controllers
{
    public class RapportController : Controller
    {
        private IProjetService _projetService;
        private ICompteService _compteService;
        private IRubriqueService _rubriqueService;
        private IRapportService _rapportService;
        private IUserService _userService;


        CultureInfo cultureInfo = CultureInfo.CurrentCulture;
        public RapportController(IProjetService projetService, IUserService userService,ICompteService compteService, IRubriqueService rubriqueService, IRapportService rapportService) { 

            _projetService = projetService;
            _compteService= compteService;
            _rubriqueService = rubriqueService;
            _rapportService = rapportService;
            _userService = userService;





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
            List<RapportModel> rapports = await _rapportService.GetListrapportByUserAndWeek(HttpContext.Session, WeekOfYear, monthOfYear, year, compte.User.Id.GetValueOrDefault(0));
          
            ViewBag.projets = projets ?? new List<ProjetModel>();
            ViewBag.lstRapportWeek = rapports;

            return View();
        }
        //GET: /<controller>
        
        public async Task<IActionResult> List()
        {
            var WeekOfYear = cultureInfo.Calendar.GetWeekOfYear(DateTime.Now, CalendarWeekRule.FirstDay, DayOfWeek.Monday);
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<RapportModel> rapports = await _rapportService.GetListAllrapportByUser(HttpContext.Session, WeekOfYear, compte.User.Id.GetValueOrDefault(0));
            List<CustomModel> historiqueRapport = new List<CustomModel>();
            if(rapports.Count() > 0)
            {
                var ListOfRapports = rapports.GroupBy(x => new { x.Semaine, x.Annee, x.Mois })
                                 .Select(g => g)
                                 .ToList();

                foreach (var item in ListOfRapports)
                {
                    CustomModel RapportGBS = new CustomModel();

                    RapportGBS.Semaine = (int)item.Key.Semaine;
                    RapportGBS.Mois = (int)item.Key.Mois;
                    RapportGBS.Annee = (int)item.Key.Annee;
                    RapportGBS.Rapports = item.ToArray();
                    historiqueRapport.Add(RapportGBS);
                }
            }
           
            ViewBag.historiqueRapport = historiqueRapport;
            return View();
        }
        // GET: /<controller>/
        public async Task<IActionResult> view(int semaine, int annee , int mois)
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);

            List <RapportModel> rapports= await _rapportService.GetListrapportByUserAndWeek(HttpContext.Session, semaine, mois, annee, compte.User.Id.GetValueOrDefault(0));
            ViewBag.rapports = rapports ?? new List<RapportModel> ();
            return View();
        }
        // GET: /<controller>/
        public async Task<IActionResult> Valider()
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<UserModel> result = await _userService.GetListUserByManagerAsync(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            var WeekOfYear = cultureInfo.Calendar.GetWeekOfYear(DateTime.Now, CalendarWeekRule.FirstDay, DayOfWeek.Monday);
            var monthOfYear = cultureInfo.Calendar.GetMonth(DateTime.Now);
            var year = cultureInfo.Calendar.GetYear(DateTime.Now);
            List<CustomRapportModel> lstRapportEquipe = new List<CustomRapportModel> ();
            foreach(UserModel item in result)
            {
                CustomRapportModel rp = new CustomRapportModel();
                List<RapportModel> rapports = await _rapportService.GetListrapportByUserAndWeek(HttpContext.Session, WeekOfYear, monthOfYear, year, item.Id.GetValueOrDefault(0));
                rp.user = item;
                rp.rapport = rapports;
                lstRapportEquipe.Add(rp);
            }

            ViewBag.lstRapportEquipe = lstRapportEquipe ?? new List<CustomRapportModel>();

            return View();
        }
        // GET: /<controller>/
        [HttpPost]
        public async Task<ActionResult> GetRubrique(ProjetModel projet)
        {
            try
            {
                List<RubriqueModel> LstRubrique = await _rubriqueService.GetListRubriqueByProjet(HttpContext.Session, projet.Id);

                return Json(LstRubrique);
            }
            catch (Exception e)
            {
                //return null;
                return Json(new { someValue = "ko" });


            }



        }
        [HttpPost]
        public async Task<ActionResult> PostRapport(List<RapportModel> customModel) { 
           try
            {
                var WeekOfYear = cultureInfo.Calendar.GetWeekOfYear(DateTime.Now, CalendarWeekRule.FirstDay, DayOfWeek.Monday);
                var monthOfYear = cultureInfo.Calendar.GetMonth(DateTime.Now);
                CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
                 var year = cultureInfo.Calendar.GetYear(DateTime.Now);
                List<RapportModel> rapports = await _rapportService.GetListrapportByUserAndWeek(HttpContext.Session, WeekOfYear, monthOfYear, year, compte.User.Id.GetValueOrDefault(0));
                foreach(RapportModel rp in rapports)
                {
                    await _rapportService.DeleteRapport(HttpContext.Session,compte.User.Id.GetValueOrDefault(0), rp.Rubrique.Id,rp.Semaine,rp.Annee);
                }
                DayOfWeek fdow = CultureInfo.CurrentCulture.DateTimeFormat.FirstDayOfWeek;
                DateTime start = DateTime.Today.AddDays(-(DateTime.Today.DayOfWeek - fdow));

                foreach (RapportModel rapport in customModel)
                {
                    rapport.DateCreation = DateTime.Now;
                    rapport.DateImputation = DateTime.Now;
                    rapport.Mois = monthOfYear;
                    rapport.User = compte.User;
                    rapport.Valider = false;
                    await _rapportService.AddAsync(HttpContext.Session, rapport);
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
