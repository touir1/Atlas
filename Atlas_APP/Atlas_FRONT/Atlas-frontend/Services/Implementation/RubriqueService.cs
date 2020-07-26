using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class RubriqueService : BaseService<RubriqueModel>, IRubriqueService
    {
        public RubriqueService(IRestAPIClient restAPIClient) : base(restAPIClient, "rubrique")
        {
        }

        public async Task<List<RubriqueModel>> GetListRubriqueByUser(ISession session, long? idProjet)
        {
            RestApiResponse<List<RubriqueModel>> result = await _client.GetAsync<List<RubriqueModel>>(session, $"rubrique/byProjet/{idProjet}");
            return result.Result;
        }
    }
}
