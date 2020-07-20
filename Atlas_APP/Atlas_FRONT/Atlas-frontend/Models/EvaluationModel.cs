using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class EvaluationModel
    {

        [JsonProperty("id")]
        public long? Id { get; set; }

        [JsonProperty("status")]
        public String Status { get; set; }

        [JsonProperty("user")]
        public UserModel User{ get; set; }

        [JsonProperty("createdBy")]
        public UserModel CreatedBy { get; set; }

        [JsonProperty("reponses")]
        public List<ResponseModel> Reponses { get; set; }
    }
}
