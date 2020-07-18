using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class FormationService : BaseService<FormationModel>, IFormationService
    {
        public FormationService(IRestAPIClient restAPIClient) : base(restAPIClient, "formation")
        {
        }
    }
}
