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
using Microsoft.AspNetCore.Mvc.Razor.Extensions;

namespace Atlas_frontend.Controllers
{
    public class CompteController : Controller
    {
        private ICompteService _compteService;
        private IUserService _userService;

        public CompteController(ICompteService compteService, IUserService userService)
        {
            _compteService = compteService;
            _userService = userService;
        }

        // GET: Compte
        public async Task<ActionResult> IndexAsync()
        {
            List<CompteModel> comptes = await _compteService.GetListAsync(HttpContext.Session);
            ViewData.Model = comptes;
            return View();
        }

        // GET: Compte/Details/5
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> DetailsAsync(int id)
        {
            CompteModel compte = await _compteService.GetAsync(HttpContext.Session, id);
            ViewData.Model = compte;
            return View();
        }

        // GET: Compte/Create
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> CreateAsync()
        {
            List<UserModel> users = await _userService.GetListAsync(HttpContext.Session);
            ViewBag.UserList = users;
            return View();
        }

        // POST: Compte/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> CreateAsync(CompteModel compte, int idUser)
        {
            try
            {
                if (!ModelState.IsValid)
                {
                    List<UserModel> users = await _userService.GetListAsync(HttpContext.Session);
                    ViewBag.UserList = users;
                    return View();
                }

                compte.User = new UserModel { Id = idUser };
                await _compteService.AddAsync(HttpContext.Session, compte);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Compte/Edit/5
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> EditAsync(int id)
        {
            CompteModel compte = await _compteService.GetAsync(HttpContext.Session, id);
            ViewData.Model = compte;
            return View();
        }

        // POST: Compte/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> EditAsync(int id, CompteModel compte)
        {
            try
            {
                if (!ModelState.IsValid)
                {
                    return View();
                }
                await _compteService.UpdateAsync(HttpContext.Session, compte);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Compte/Delete/5
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> DeleteAsync(int id)
        {
            CompteModel compte = await _compteService.GetAsync(HttpContext.Session, id);
            ViewData.Model = compte;
            return View();
        }

        // POST: Compte/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> DeleteAsync(int id, CompteModel compte)
        {
            try
            {
                await _compteService.DeleteAsync(HttpContext.Session, id);

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}