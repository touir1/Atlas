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
    public class UserController : Controller
    {
        private IUserService _userService;
        public UserController(IUserService userService)
        {
            _userService = userService;
        }
        // GET: User
        public async Task<ActionResult> Index()
        {
            List<UserModel> users = await _userService.GetListAsync(HttpContext.Session);
            ViewData.Model = users;
            return View();
        }

        // GET: User/Details/5
        public async Task<ActionResult> Details(long id)
        {
            UserModel user = await _userService.GetAsync(HttpContext.Session, id);
            ViewData.Model = user;
            return View();
        }

        // GET: User/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: User/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> CreateAsync(UserModel model)
        {
            try
            {
                // TODO: Add insert logic here
                await _userService.AddAsync(HttpContext.Session, model);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: User/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            UserModel user = await _userService.GetAsync(HttpContext.Session, id);
            ViewData.Model = user;
            return View();
        }

        // POST: User/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> EditAsync(int id, UserModel model)
        {
            try
            {
                // TODO: Add update logic here
                model.Id = id;
                await _userService.UpdateAsync(HttpContext.Session, model);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: User/Delete/5
        public async Task<ActionResult> DeleteAsync(int id)
        {
            UserModel user = await _userService.GetAsync(HttpContext.Session, id);
            ViewData.Model = user;
            return View();
        }

        // POST: User/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> DeleteAsync(int id, IFormCollection collection)
        {
            try
            {
                await _userService.DeleteAsync(HttpContext.Session, id);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}