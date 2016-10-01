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
    public class tblOrderItemsController : Controller
    {
        private Perishableapp db = new Perishableapp();

        // GET: tblOrderItems
        public ActionResult Index()
        {
            var tblOrderItems = db.tblOrderItems.Include(t => t.tblOrder);
            return View(tblOrderItems.ToList());
        }

        // GET: tblOrderItems/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblOrderItem tblOrderItem = db.tblOrderItems.Find(id);
            if (tblOrderItem == null)
            {
                return HttpNotFound();
            }
            return View(tblOrderItem);
        }

        // GET: tblOrderItems/Create
        public ActionResult Create()
        {
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id");
            return View();
        }

        // POST: tblOrderItems/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Name,Quantity,fk_OrderID")] tblOrderItem tblOrderItem)
        {
            if (ModelState.IsValid)
            {
                db.tblOrderItems.Add(tblOrderItem);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id", tblOrderItem.fk_OrderID);
            return View(tblOrderItem);
        }

        // GET: tblOrderItems/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblOrderItem tblOrderItem = db.tblOrderItems.Find(id);
            if (tblOrderItem == null)
            {
                return HttpNotFound();
            }
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id", tblOrderItem.fk_OrderID);
            return View(tblOrderItem);
        }

        // POST: tblOrderItems/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name,Quantity,fk_OrderID")] tblOrderItem tblOrderItem)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tblOrderItem).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.fk_OrderID = new SelectList(db.tblOrders, "Id", "Id", tblOrderItem.fk_OrderID);
            return View(tblOrderItem);
        }

        // GET: tblOrderItems/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblOrderItem tblOrderItem = db.tblOrderItems.Find(id);
            if (tblOrderItem == null)
            {
                return HttpNotFound();
            }
            return View(tblOrderItem);
        }

        // POST: tblOrderItems/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            tblOrderItem tblOrderItem = db.tblOrderItems.Find(id);
            db.tblOrderItems.Remove(tblOrderItem);
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
