using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IConfigurationService : IBaseEntityService<ConfigurationModel>
    {
        public Task<ConfigurationModel> GetByKeyAsync(ISession session, string key);
        public Task InitConfigurationAsync(ISession session);
    }
}
