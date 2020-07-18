using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class MissionService : BaseService<MissionModel>, IMissionService
    {
        public MissionService(IRestAPIClient restAPIClient) : base(restAPIClient, "mission")
        {
        }
    }
}
