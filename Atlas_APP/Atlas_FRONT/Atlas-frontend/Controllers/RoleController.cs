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
    public class RoleController : Controller
    {
        private IRoleService _roleService;
        public RoleController(IRoleService roleService)
        {
            _roleService = roleService;
        }
        // GET: Role
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> IndexAsync()
        {
            List<RoleModel> roles = await _roleService.GetListAsync(HttpContext.Session);
            ViewData.Model = roles;
            return View();
        }

        // GET: Role/Create
        [Authorize(new[] { RankEnum.Administrator })]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Role/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> CreateAsync(RoleModel role)
        {
            try
            {
                if (!string.IsNullOrEmpty(role.Libelle))
                {
                    bool exists = await _roleService.ExistsRole(HttpContext.Session, role.Libelle);
                    if (exists)
                    {
                        ModelState.AddModelError(nameof(role.Libelle), "role already exists, please choose another");
                    }

                }
                if (!ModelState.IsValid)
                {
                    return View();
                }
                await _roleService.AddAsync(HttpContext.Session, role);

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Role/Delete/5
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> DeleteAsync(int id)
        {
            RoleModel role = await _roleService.GetAsync(HttpContext.Session, id);
            ViewData.Model = role;
            return View();
        }

        // POST: Role/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> DeleteAsync(int id, IFormCollection collection)
        {
            try
            {
                await _roleService.DeleteAsync(HttpContext.Session, id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}