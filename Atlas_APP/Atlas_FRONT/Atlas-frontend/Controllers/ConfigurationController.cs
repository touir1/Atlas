using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.Attributes;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Atlas_frontend.Controllers
{
    public class ConfigurationController : Controller
    {
        private IConfigurationService _configurationService;

        public ConfigurationController(IConfigurationService configurationService)
        {
            _configurationService = configurationService;
        }


        // GET: Configuration
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> IndexAsync()
        {
            List<ConfigurationModel> configuration = await _configurationService.GetListAsync(HttpContext.Session);
            ViewData.Model = configuration;
            return View();
        }

        // GET: Configuration/Edit/5
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> EditAsync(int id)
        {
            ConfigurationModel configuration = await _configurationService.GetAsync(HttpContext.Session, id);
            ViewData.Model = configuration;
            return View();
        }

        // POST: Configuration/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> EditAsync(int id, ConfigurationModel configuration)
        {
            try
            {
                await _configurationService.UpdateAsync(HttpContext.Session, configuration);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}