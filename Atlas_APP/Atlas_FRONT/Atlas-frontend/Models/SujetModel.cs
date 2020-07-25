using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class SujetModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("titre")]
        public String Titre { get; set; }
        [JsonProperty("coeficient")]
        public int? Coeficient { get; set; }
        [JsonProperty("noter")]
        public Boolean Noter { get; set; }
        [JsonProperty("questions")]
        public List<QuestionModel> Questions { get; set; }
    }
}
