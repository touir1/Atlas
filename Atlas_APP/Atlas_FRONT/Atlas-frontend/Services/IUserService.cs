using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    interface IUserService : IBaseEntityService<UserModel>
    {
        public Task<List<UserModel>> GetListUserByManagerAsync(ISession session, long idManager);

    }
}
