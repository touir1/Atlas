﻿using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IRoleService: IBaseEntityService<RoleModel>
    {
        public Task<Boolean> ExistsRole(ISession session, string label);
    }
}
