using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Mail
{
    public interface IMailService
    {
        public Task<bool> SendMailAsync(ISession session, string to, string subject, string body, bool isHTML);
        public Task<bool> SendMailAsync(ISession session, string to, string subject, string fileName, Dictionary<String, String> configuration);
    }
}
