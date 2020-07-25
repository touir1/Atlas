using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class RapportService : BaseService<RapportModel>, IRapportService
    {
        public RapportService(IRestAPIClient restAPIClient) : base(restAPIClient, "rapport")
        {
        }

        public async Task<List<RapportModel>> GetListrapportByUserAndWeek(ISession session, int? semaine, int? mois, int? annee, long? idUser)
        {
            RestApiResponse<List<RapportModel>> result = await _client.GetAsync<List<RapportModel>>(session, $"rapport/RapportsBysemaineAndUser/{semaine}/{mois}/{annee}/{idUser}");
            return result.Result;
        }
    }
}
