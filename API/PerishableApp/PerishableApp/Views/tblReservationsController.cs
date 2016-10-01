using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PerishableApp.Models;

namespace PerishableApp.Views
{
    public class tblReservationsController : Controller
    {
        private Perishableapp db = new Perishableapp();

        // GET: tblReservations
        public ActionResult Index()
        {
            var tblReservations = db.tblReservations.Include(t => t.tblCharity).Include(t => t.tblOrder);
            return View(tblReservations.ToList());
        }

        // GET: tblReservations/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblReservation tblReservation = db.tblReservations.Find(id);
            if (tblReservation == null)
            {
                return HttpNotFound();
            }
            return View(tblReservation);
        }

        // GET: tblReservations/Create
        public ActionResult Create()
        {
            ViewBag.fk_CharityID = new SelectList(db.tblCharities, "Id", "Id");
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id");
            return View();
        }

        // POST: tblReservations/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,DateTime,isActive,fk_OrderID,fk_CharityID")] tblReservation tblReservation)
        {
            if (ModelState.IsValid)
            {
                db.tblReservations.Add(tblReservation);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.fk_CharityID = new SelectList(db.tblCharities, "Id", "Id", tblReservation.fk_CharityID);
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id", tblReservation.fk_OrderID);
            return View(tblReservation);
        }

        // GET: tblReservations/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblReservation tblReservation = db.tblReservations.Find(id);
            if (tblReservation == null)
            {
                return HttpNotFound();
            }
            ViewBag.fk_CharityID = new SelectList(db.tblCharities, "Id", "Id", tblReservation.fk_CharityID);
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id", tblReservation.fk_OrderID);
            return View(tblReservation);
        }

        // POST: tblReservations/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,DateTime,isActive,fk_OrderID,fk_CharityID")] tblReservation tblReservation)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tblReservation).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.fk_CharityID = new SelectList(db.tblCharities, "Id", "Id", tblReservation.fk_CharityID);
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id", tblReservation.fk_OrderID);
            return View(tblReservation);
        }

        // GET: tblReservations/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblReservation tblReservation = db.tblReservations.Find(id);
            if (tblReservation == null)
            {
                return HttpNotFound();
            }
            return View(tblReservation);
        }

        // POST: tblReservations/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            tblReservation tblReservation = db.tblReservations.Find(id);
            db.tblReservations.Remove(tblReservation);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
