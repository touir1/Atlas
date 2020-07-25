using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Atlas_frontend.Controllers
{
    public class RapportController : Controller
    {
        private IProjetService _projetService;
        private ICompteService _compteService;
        public RapportController(IProjetService projetService, ICompteService compteService) { 

            _projetService = projetService;
            _compteService= compteService;

    }
        // GET: /<controller>/
        public IActionResult Index()
        {   
   
            CultureInfo cultureInfo = CultureInfo.CurrentCulture;
            var WeekOfYear = cultureInfo.Calendar.GetWeekOfYear(DateTime.Now, CalendarWeekRule.FirstDay, DayOfWeek.Monday);
            string dayOfWeek = DateTime.Now.ToString("dddd", new System.Globalization.CultureInfo("fr-FR"));
            var monthOfYear= cultureInfo.Calendar.GetMonth(DateTime.Now);
            var year = cultureInfo.Calendar.GetYear(DateTime.Now);

            ViewBag.WeekOfYear = WeekOfYear;
            ViewBag.DayOfweek = dayOfWeek;
            ViewBag.monthOfYear = monthOfYear;
            ViewBag.year = year;

            DayOfWeek fdow = CultureInfo.CurrentCulture.DateTimeFormat.FirstDayOfWeek ;
            DateTime start = DateTime.Today.AddDays(-(DateTime.Today.DayOfWeek - fdow));
            ViewBag.start = start.AddDays(1);

            return View();
        }
    }
}
