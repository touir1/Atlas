﻿using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class AbsenceService : BaseService<AbsenceModel>, IAbsenceService
    {
        public AbsenceService(IRestAPIClient restAPIClient) : base(restAPIClient, "absence")
        {
        }
    }
}
