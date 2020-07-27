using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class ChoixService : BaseService<ChoixModel>, IChoixService
    {
        public ChoixService(IRestAPIClient restAPIClient) : base(restAPIClient, "choix")
        {
          
        }

        public async Task<List<ChoixModel>> getChoixByQuestion(ISession session, long idQuestion)
        {
            var result = await _client.GetAsync<List<ChoixModel>>(session, $"{_baseServiceUrl}/Question/{idQuestion}");
            return result.Result;
        }
    }
}
