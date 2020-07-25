using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Atlas_frontend.Services;
using Atlas_frontend.Utils.Attributes;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;


namespace Atlas_frontend.Controllers
{
    public class UserController : Controller
    {
        private IUserService _userService;
        private IWebHostEnvironment _webHostEnvironment;

        public UserController(IUserService userService, IWebHostEnvironment hostEnvironment)
        {
            _userService = userService;
            _webHostEnvironment = hostEnvironment;
        }
        // GET: User
        [Authorize(new[] { RankEnum.Administrator })]
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
        [Authorize(new[] { RankEnum.Administrator })]
        public ActionResult Create()
        {
            return View();
        }

        // POST: User/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> CreateAsync(UserModel model)
        {
            try
            {
                if (!ModelState.IsValid)
                {
                    return View();
                }
                string uniqueFileName = UploadedFile(model);
                model.Image = uniqueFileName;

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
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> Edit(int id)
        {
            UserModel user = await _userService.GetAsync(HttpContext.Session, id);
            ViewData.Model = user;
            return View();
        }

        // POST: User/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> EditAsync(int id, UserModel model)
        {
            try
            {
                if (!ModelState.IsValid)
                {
                    return View();
                }
                // TODO: Add update logic here
                string uniqueFileName = UploadedFile(model);
                model.Image = uniqueFileName;
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
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> DeleteAsync(int id)
        {
            UserModel user = await _userService.GetAsync(HttpContext.Session, id);
            ViewData.Model = user;
            return View();
        }

        // POST: User/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
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

        private string UploadedFile(UserModel model)
        {
            string uniqueFileName = null;

            if (model.ProfileImage != null)
            {
                string uploadsFolder = Path.Combine(_webHostEnvironment.WebRootPath, "images/profiles");
                uniqueFileName = Guid.NewGuid().ToString() + "_" + model.ProfileImage.FileName;
                string filePath = Path.Combine(uploadsFolder, uniqueFileName);
                using (var fileStream = new FileStream(filePath, FileMode.Create))
                {
                    model.ProfileImage.CopyTo(fileStream);
                }
            }
            return uniqueFileName;
        }
    }
}