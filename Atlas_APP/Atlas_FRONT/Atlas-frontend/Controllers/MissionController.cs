using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Atlas_frontend.Controllers
{
    public class MissionController : Controller
    {
        private IMissionService _missionService;
        private IProjetService _projetService;
        private ICompteService _compteService;
        private IUserService _userService;

        public MissionController(IMissionService missionService,IProjetService projetService,ICompteService compteService,IUserService userService)
        {
            _missionService = missionService;
            _projetService = projetService;
            _compteService = compteService;
            _userService = userService;
        }


        // GET: Mission
        public async Task<ActionResult> IndexAsync()
        {
            List<MissionModel> missions = await _missionService.GetListAsync(HttpContext.Session);
            ViewData.Model = missions; 
            return View();
        }

        // GET: Mission/Details/5
        public async Task<ActionResult> DetailsAsync(int id)
            
        {
            MissionModel mission = await _missionService.GetAsync(HttpContext.Session, id);
            ViewData.Model = mission;
            return View();
        }

        // GET: Mission/Create
        public async Task<ActionResult> CreateAsync()
        {
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<ProjetModel> result = await _projetService.GetListProjectByManager(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.listprojet = result ?? new List<ProjetModel>();
            return View();
        }

        // POST: Mission/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> CreateAsync(MissionModel model, long? projectId)
        {
            try
            {
               if (!ModelState.IsValid) {

                    return View();               
                }
                model.Projet = new ProjetModel { Id = projectId };
               
                await _missionService.AddAsync(HttpContext.Session, model);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Mission/Edit/5
        public async Task<ActionResult> EditAsync(int id)
        {
            MissionModel mission = await _missionService.GetAsync(HttpContext.Session, id);
            ViewData.Model = mission;
            CompteModel compte = _compteService.GetConnectedCompte(HttpContext.Session);
            List<ProjetModel> result = await _projetService.GetListProjectByManager(HttpContext.Session, compte.User.Id.GetValueOrDefault(0));
            ViewBag.listprojet = result ?? new List<ProjetModel>();
            return View();
        }

        // POST: Mission/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> EditAsync(int id, MissionModel model, long? projectId)
        {
            try
            {
                if (!ModelState.IsValid)
                {
                    return View();
                }
                // TODO: Add update logic here
               
                model.Id = id;
                model.Projet = new ProjetModel { Id = projectId };
                await _missionService.UpdateAsync(HttpContext.Session,model);

                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: Mission/Delete/5
        public async Task<ActionResult> DeleteAsync(int id)
        {
            MissionModel mission = await _missionService.GetAsync(HttpContext.Session, id);
            ViewData.Model = mission; 
            return View();
        }

        // POST: Mission/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Delete(int id, IFormCollection collection)
        {
            try
            {
              await  _missionService.DeleteAsync(HttpContext.Session, id);
                 
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}