using Atlas_frontend.Models;
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
    }
}
