using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class UserService : BaseService<UserModel>, IUserService
    {
        public UserService(IRestAPIClient restAPIClient) : base(restAPIClient, "user")
        {
        }

        public async Task<List<UserModel>> GetListUserByManagerAsync(ISession session, long idManager)
        {
            
          
                RestApiResponse<List<UserModel>> result = null;
                result = await _client.GetAsync<List<UserModel>>(session, $"user/getUsersByManager/{idManager}");
                return result.Result;
           
        }
    }
}
