using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class QuestionService : BaseService<QuestionModel>, IQuestionService
    {
        public QuestionService(IRestAPIClient restAPIClient) : base(restAPIClient, "question")
        {
        }

        public async Task<List<QuestionModel>> GetQuestionBySujet(ISession session, long? idSujet)
        {
            RestApiResponse<List<QuestionModel>> result = await _client.GetAsync<List<QuestionModel>>(session, $"{_baseServiceUrl}/bySujet/{idSujet}");
            return result.Result;
        }
    }
}
