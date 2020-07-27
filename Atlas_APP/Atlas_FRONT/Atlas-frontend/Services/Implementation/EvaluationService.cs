using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class EvaluationService : BaseService<EvaluationModel>, IEvaluationService
    {
        public EvaluationService(IRestAPIClient restAPIClient) : base(restAPIClient, "evaluation")
        {


        }

        public async Task AffecterUserToEvaluationAsync(ISession session, long? idEvaluation, long? idUser)
        {

            var result = await _client.PutAsync<Object, Object>(session, $"{_baseServiceUrl}/Affecter/{idEvaluation}/{idUser}", null);


        }

        public async Task<List<UserModel>> GetListUserByEvalAsync(ISession session, long idEvaluation)
        {
            var result = await _client.GetAsync<List<UserModel>>(session, $"{_baseServiceUrl}/Members/{idEvaluation}");
            return result.Result;
        }

        public async Task<List<SujetModel>> GetListSujetByEvalAsync(ISession session, long idEvaluation)
        {
            var result = await _client.GetAsync<List<SujetModel>>(session, $"{_baseServiceUrl}/Sujets/{idEvaluation}");
            return result.Result;
        }

        public async Task<List<EvaluationModel>> GetListEvalByUser(ISession session, long idUser)
        {
            var result = await _client.GetAsync<List<EvaluationModel>>(session, $"{_baseServiceUrl}/Members/User/{idUser}");
            return result.Result;
        }
    }
}
