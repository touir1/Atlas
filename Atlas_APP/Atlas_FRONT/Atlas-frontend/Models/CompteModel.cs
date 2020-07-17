using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class CompteModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }
        [JsonProperty("username")]
        public string Username { get; set; }
        [JsonProperty("password")]
        public string Password { get; set; }
        [JsonProperty("token")]
        public string Token { get; set; }
        [JsonProperty("roles")]
        public List<RoleModel> Roles { get; set; }
        [JsonProperty("user")]
        public UserModel User { get; set; }


    }
}
