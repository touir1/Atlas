using Atlas_frontend.Models;
using Atlas_frontend.Models.Enums;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface ICompteService : IBaseEntityService<CompteModel>
    {
        public Task<CompteModel> LoginAsync(ISession session, string username, string password);
        public Task<Boolean> LogoutAsync(ISession session);
        public CompteModel GetConnectedCompte(ISession session);
        public Boolean IsConnected(ISession session);
        public Boolean HasRole(ISession session, RankEnum role);
        public Boolean HasRole(ISession session, string roleLabel);
        public Boolean HasRole(ISession session, long roleId);
        public Task<Boolean> ExistsUsername(ISession session, string username); 
    }
}
