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

        public void AddAsync(CompteModel entity)
        {
            throw new NotImplementedException();
        }

        public void DeleteAsync(CompteModel entity)
        {
            throw new NotImplementedException();
        }

        public Task<CompteModel> GetAsync(long? id, long? secondId = null)
        {
            throw new NotImplementedException();
        }

        public Task<List<CompteModel>> GetListAsync()
        {
            throw new NotImplementedException();
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

        public void UpdateAsync(CompteModel entity)
        {
            throw new NotImplementedException();
        }
    }
}
