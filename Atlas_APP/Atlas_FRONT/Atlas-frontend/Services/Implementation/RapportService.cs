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

        public async Task DeleteRapport(ISession session, long? idUser, long? idRubrique, int? semaine, int? annee)
        {
            await _client.GetAsync<WSBaseResult>(session, $"{_baseServiceUrl}/delete/{idUser}/{idRubrique}/{semaine}/{annee}");
        }

        public  async   Task<List<RapportModel>> GetListAllrapportByUser(ISession session, int? semaine, long? idUser)
        {
            RestApiResponse<List<RapportModel>> result = await _client.GetAsync<List<RapportModel>>(session, $"rapport/rapportAll/{idUser}/{semaine}");
            return result.Result;
        }

        public async Task<List<RapportModel>> GetListrapportByUserAndWeek(ISession session, int? semaine, int? mois, int? annee, long? idUser)
        {
            RestApiResponse<List<RapportModel>> result = await _client.GetAsync<List<RapportModel>>(session, $"rapport/RapportsBysemaineAndUser/{semaine}/{mois}/{annee}/{idUser}");
            return result.Result;
        }
    }
}
