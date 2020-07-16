using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class CompteModel
    {
        public long id { get;set }
        public string username { get; set; }
        public string password { get; set; }
        public List<Role> roles { get; set; }
        public UserModel user { get; set; }


    }
}
