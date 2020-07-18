using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class ReponseService : BaseService<ResponseModel>, IReponseService
    {
        public ReponseService(IRestAPIClient restAPIClient) : base(restAPIClient, "reponse")
        {
        }
    }
}
