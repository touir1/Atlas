using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class CompteModel
    {
        public long Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public List<RoleModel> Roles { get; set; }
        public UserModel User { get; set; }


    }
}
