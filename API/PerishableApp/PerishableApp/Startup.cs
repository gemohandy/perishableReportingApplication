using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(PerishableApp.Startup))]
namespace PerishableApp
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
