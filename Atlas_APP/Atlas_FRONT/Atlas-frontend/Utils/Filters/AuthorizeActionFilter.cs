using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Atlas_frontend.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Routing;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Filters
{
    public class AuthorizeActionFilter : IAuthorizationFilter
    {
        private RankEnum[] _authorizedRanks;
        private IPermissionService _permissionService;
        private ICompteService _compteService;

        public AuthorizeActionFilter(IPermissionService permissionService, ICompteService compteService, RankEnum[] authorizedRanks)
        {
            _permissionService = permissionService;
            _compteService = compteService;
            _authorizedRanks = authorizedRanks;
        }
        public void OnAuthorization(AuthorizationFilterContext context)
        {
            if (_authorizedRanks == null || _authorizedRanks.Length == 0 || _authorizedRanks.Contains(RankEnum.Anonymous))
            {
                return;
            }
            else if (_authorizedRanks.Contains(RankEnum.None))
            {
                context.Result = new UnauthorizedResult();
            }
            else
            {
                if (!_compteService.IsConnected(context.HttpContext.Session))
                {
                    
                    context.Result = new RedirectToRouteResult(
                        new RouteValueDictionary(new { controller = "Login", action = "Index" })
                    );
                }
                else if (_authorizedRanks.Contains(RankEnum.Connected))
                {
                    return;
                }
                else if (!CheckIfHasPermission(context))
                {
                    context.Result = new UnauthorizedResult();
                }
                return;
            }
        }
        
        private bool CheckIfHasPermission(AuthorizationFilterContext context)
        {
            CompteModel compte = _compteService.GetConnectedCompte(context.HttpContext.Session);
            foreach(RankEnum rank in _authorizedRanks)
            {
                if (_compteService.HasRole(context.HttpContext.Session, rank)) return true;
            }
            
            return false;
        }
    }
}
