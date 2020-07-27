using Atlas_frontend.Models;
using Atlas_frontend.Service;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
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

        public async Task<List<ResponseModel>> GetReponseByEvalAsync(ISession session, long idEvaluation)
        {
            var result = await _client.GetAsync<List<ResponseModel>>(session, $"{_baseServiceUrl}/Evaluation/{idEvaluation}");
            return result.Result;
        }
    }
}
