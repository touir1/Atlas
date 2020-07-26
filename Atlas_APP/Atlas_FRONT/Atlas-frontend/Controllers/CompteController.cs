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
        private IRoleService _roleService;

        public CompteController(ICompteService compteService, IUserService userService, IRoleService roleService)
        {
            _compteService = compteService;
            _userService = userService;
            _roleService = roleService;
        }

        // GET: Compte
        [Authorize(new[] { RankEnum.Administrator })]
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
            List<RoleModel> roles = await _roleService.GetListAsync(HttpContext.Session);
            ViewBag.RoleList = roles;
            return View();
        }

        // POST: Compte/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> CreateAsync(CompteModel compte, int idUser, List<long> roles)
        {
            try
            {
                if (!string.IsNullOrEmpty(compte.Username))
                {
                    bool exists = await _compteService.ExistsUsername(HttpContext.Session, compte.Username);
                    if (exists)
                    {
                        ModelState.AddModelError(nameof(compte.Username), "Username already exists, please choose another");
                    }
                   
                }

                if (!ModelState.IsValid)
                {
                    List<UserModel> users = await _userService.GetListAsync(HttpContext.Session);
                    ViewBag.UserList = users;
                    List<RoleModel> rolesList = await _roleService.GetListAsync(HttpContext.Session);
                    ViewBag.RoleList = rolesList;
                    return View();
                }
                if(roles != null)
                {
                    List<RoleModel> rolesList = new List<RoleModel>();
                    foreach(long idRole in roles)
                    {
                        rolesList.Add(new RoleModel { Id = idRole });
                    }
                    compte.Roles = rolesList;
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
            List<RoleModel> roles = await _roleService.GetListAsync(HttpContext.Session);
            ViewBag.RoleList = roles;
            return View();
        }

        // POST: Compte/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(new[] { RankEnum.Administrator })]
        public async Task<ActionResult> EditAsync(int id, CompteModel compte, List<long> roles)
        {
            try
            {
                CompteModel databaseCompte = await _compteService.GetAsync(HttpContext.Session, id);
                if (compte.Password == null || compte.Password.Trim().Length == 0)
                {
                    ModelState.Remove("Password");
                }
                if (!ModelState.IsValid)
                {
                    ViewData.Model = compte;
                    List<RoleModel> rolesList = await _roleService.GetListAsync(HttpContext.Session);
                    ViewBag.RoleList = rolesList;
                    return View();
                }
                

                if (roles != null)
                {
                    List<RoleModel> rolesList = new List<RoleModel>();
                    foreach (long idRole in roles)
                    {
                        rolesList.Add(new RoleModel { Id = idRole });
                    }
                    compte.Roles = rolesList;
                }
                if(compte.Password == null)
                {
                    compte.Password = databaseCompte.Password;
                }
                if(compte.User == null)
                {
                    compte.User = databaseCompte.User;
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