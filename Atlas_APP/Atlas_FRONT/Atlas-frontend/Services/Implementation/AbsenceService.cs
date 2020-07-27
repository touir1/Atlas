using Atlas_frontend.Models;
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

        public async Task<List<AbsenceModel>> GetListAbsenceByStatusForManager(ISession session, string status, long idManager)
        {
            RestApiResponse<List<AbsenceModel>> response = await _client.GetAsync<List<AbsenceModel>>(session, $"{_baseServiceUrl}/byStatusForManager/{idManager}/{status}");

            return response.Result;
        }

        public async Task<List<AbsenceModel>> GetListAbsenceByStatus(ISession session, string status)
        {
            RestApiResponse<List<AbsenceModel>> response = await _client.GetAsync<List<AbsenceModel>>(session, $"{_baseServiceUrl}/byStatus/{status}");

            return response.Result;
        }

        public async Task<List<AbsenceModel>> GetListAbsenceByStatusForUser(ISession session, string status, long idUser)
        {
            RestApiResponse<List<AbsenceModel>> response = await _client.GetAsync<List<AbsenceModel>>(session, $"{_baseServiceUrl}/byStatusForUser/{idUser}/{status}");

            return response.Result;
        }

        public async Task<List<AbsenceModel>> GetListAbsenceForUser(ISession session, long idUser)
        {
            RestApiResponse<List<AbsenceModel>> response = await _client.GetAsync<List<AbsenceModel>>(session, $"{_baseServiceUrl}/forUser/{idUser}");

            return response.Result;
        }

        public async Task<float> GetSoldeCongee(ISession session, long idUser)
        {
            try
            {
                RestApiResponse<WSBaseResult> result = await _client.GetAsync<WSBaseResult>(session, $"{_baseServiceUrl}/soldeCongee/{idUser}");
                if (result != null && result.Result != null) return result.Result.SoldeCongee;
            }
            catch (Exception)
            {
                return 0;
            }
            return 0;
        }

        public async Task<float> GetSoldeCongeeTotale(ISession session, long idUser)
        {
            try
            {
                RestApiResponse<WSBaseResult> result = await _client.GetAsync<WSBaseResult>(session, $"{_baseServiceUrl}/soldeCongeeTotale/{idUser}");
                if (result != null && result.Result != null) return result.Result.SoldeCongee;
            }
            catch (Exception)
            {
                return 0;
            }
            return 0;
        }
    }
}
