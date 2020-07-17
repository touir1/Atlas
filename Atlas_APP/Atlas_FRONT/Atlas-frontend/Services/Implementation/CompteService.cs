using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class CompteService : ICompteService
    {
        private IRestAPIClient _client;
        public CompteService(IRestAPIClient restAPIClient) :base()
        {
            _client = restAPIClient;
        }

        public async void AddAsync(CompteModel entity)
        {
           await _client.PostAsync<CompteModel, Boolean>(null,"compte", entity);
        }

        public async void DeleteAsync(CompteModel entity)
        {
            await _client.DeleteAsync<CompteModel, Boolean>(null, $"compte/{entity.Id}");
        }

        public async Task<CompteModel> GetAsync(long? id, long? secondId = null)
        {
            RestApiResponse<CompteModel> result = await _client.GetAsync<CompteModel>(null, $"compte/{id}");
            return result.Result;
        }

        public async Task<List<CompteModel>> GetListAsync()
        {
            RestApiResponse<List<CompteModel>> result = await _client.GetAsync<List<CompteModel>>(null, "compte");
            return result.Result;
        }

        public async Task<CompteModel> LoginAsync(ISession session, string username, string password)
        {
            RestApiResponse<CompteModel> cpt = await _client.PostAsync<CompteModel, CompteModel>(session , "compte/login", new CompteModel
            {
                Username = username,
                Password = password
            });

            session.SetString(RestAPIClient.TokenKey, cpt.Result.Token);

            return cpt.Result;
        }

        public async void UpdateAsync(CompteModel entity)
        {
            RestApiResponse<CompteModel> result = await _client.PutAsync<CompteModel, CompteModel>(null, "compte", entity);
        }
    }
}
